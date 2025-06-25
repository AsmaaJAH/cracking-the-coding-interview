/*
7.7 Chat Server: Explain how you would design a chat server. In particular, provide details about the
various backend components, classes, and methods. What would be the hardest problems to solve? 
*/
package ch7_oop_design;

import java.util.*;

public class ChatServer7 {
    static class User {
        private int id;
        private String name;
        private Status status;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
            this.status = Status.OFFLINE;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public int getId() { return id; }
        public String getName() { return name; }
        public Status getStatus() { return status; }
    }

    enum Status {
        ONLINE, OFFLINE, AWAY, BUSY
    }

    static class Message {
        private int id;
        private int senderId;
        private int receiverId; 
        private String content;
        private Date timestamp;

        public Message(int id, int senderId, int receiverId, String content) {
            this.id = id;
            this.senderId = senderId;
            this.receiverId = receiverId;
            this.content = content;
            this.timestamp = new Date();
        }

        public String toString() {
            return "[" + timestamp + "] User " + senderId + ": " + content;
        }
    }

    static class Chat {
        private int chatId;
        private List<Integer> participantIds = new ArrayList<>();
        private List<Message> messages = new ArrayList<>();

        public Chat(int chatId, List<Integer> users) {
            this.chatId = chatId;
            this.participantIds.addAll(users);
        }

        public void sendMessage(Message message) {
            messages.add(message);
        }

        public List<Message> getMessages() {
            return messages;
        }
    }

    static class Connection {
        private User user;

        public Connection(User user) {
            this.user = user;
        }

        public void send(String data) {
            System.out.println("Sending to " + user.getName() + ": " + data);
        }
    }

    static class ChatServer {// server as controller 
        private Map<Integer, User> users = new HashMap<>();
        private Map<Integer, Chat> chats = new HashMap<>();
        private Map<Integer, Connection> connections = new HashMap<>();

        private int nextChatId = 1;
        private int nextMessageId = 1;

        public void registerUser(User user) {
            users.put(user.getId(), user);
        }

        public void login(int userId) {
            User user = users.get(userId);
            if (user != null) {
                user.setStatus(Status.ONLINE);
                connections.put(userId, new Connection(user));
                System.out.println(user.getName() + " is now online.");
            }
        }

        public int createChat(int user1, int user2) {
            int chatId = nextChatId++;
            Chat chat = new Chat(chatId, Arrays.asList(user1, user2));
            chats.put(chatId, chat);
            return chatId;
        }

        public void sendMessage(int chatId, int senderId, String content) {
            Chat chat = chats.get(chatId);
            if (chat != null) {
                int receiverId = chat.participantIds.stream()
                                    .filter(id -> id != senderId)
                                    .findFirst().orElse(-1);
                Message msg = new Message(nextMessageId++, senderId, receiverId, content);
                chat.sendMessage(msg);
                deliver(receiverId, msg);
            }
        }

        private void deliver(int receiverId, Message msg) {
            Connection conn = connections.get(receiverId);
            if (conn != null) {
                conn.send(msg.toString());
            } else {
                System.out.println("User " + receiverId + " is offline. Store message for later.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Chat Server");
        ChatServer server = new ChatServer();

        User alice = new User(1, "Asmaa");
        User bob = new User(2, "Gamal");

        server.registerUser(alice);
        server.registerUser(bob);

        server.login(1);
        server.login(2);

        int chatId = server.createChat(1, 2);
        server.sendMessage(chatId, 1, "Hi Asmaa!");
        server.sendMessage(chatId, 2, "Hey Gamal!");
    }
}
