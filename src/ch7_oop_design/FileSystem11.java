/*
7.11 File System: Explain the data structures and algorithms that you would use to design an in-memory
file system. Illustrate with an example in code where possible.
 */
package ch7_oop_design;

import java.util.*;

public class FileSystem11 {

    abstract static class Entry {
        protected String name;
        protected Directory parent;

        public Entry(String name, Directory parent) {
            this.name = name;
            this.parent = parent;
        }

        public String getName() {
            return name;
        }

        public String getFullPath() {
            if (parent == null)
                return name;
            return parent.getFullPath() + "/" + name;
        }

        public abstract boolean isDirectory();// true if folder(directory), false if file

    }

    static class File extends Entry {
        private String content;

        public File(String name, Directory parent) {
            super(name, parent);
            this.content = "";
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public boolean isDirectory() {
            return false;
        }
    }

    static class Directory extends Entry {
        private List<Entry> children;

        public Directory(String name, Directory parent) {
            super(name, parent);
            children = new ArrayList<>();
        }

        public List<Entry> getChildren() {
            return children;
        }

        @Override
        public boolean isDirectory() {
            return true;
        }

        public Entry getEntry(String name) {
            for (Entry e : children) {
                if (e.getName().equals(name))
                    return e;
            }
            return null;
        }

        public void addEntry(Entry entry) {
            children.add(entry);
        }

    }

    static class FileSystem {
        private Directory root;

        public FileSystem() {
            root = new Directory("root", null);
        }

        public Directory getRoot() {
            return root;
        }

        public void delete(String path) {
        }

        public void move(String sourcePath, String destinationPath) {
        }

        private Directory findDirectory(String path) {
            if (path.equals("/") || path.equals("root"))
                return root;

            String[] parts = path.split("/");
            Directory current = root;

            for (String part : parts) {
                if (part.isEmpty())
                    continue;
                Entry entry = current.getEntry(part);
                if (entry == null || !entry.isDirectory())
                    return null;
                current = (Directory) entry;
            }

            return current;
        }

        public List<String> list(String path) {
            Directory dir = findDirectory(path);
            if (dir == null)
                return new ArrayList<>();
            List<String> names = new ArrayList<>();
            for (Entry e : dir.getChildren()) {
                names.add(e.getName());
            }
            return names;
        }

        public File createFile(String path, String name) {
            Directory dir = findDirectory(path);
            if (dir == null)
                return null;
            File file = new File(name, dir);
            dir.addEntry(file);
            return file;
        }

        public Directory createDirectory(String path, String name) {
            Directory parentDirectory = findDirectory(path);
            if (parentDirectory == null)
                return null;
            Directory newDir = new Directory(name, parentDirectory);
            parentDirectory.addEntry(newDir);
            return newDir;
        }

    }
}
