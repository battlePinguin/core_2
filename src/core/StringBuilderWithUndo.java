package core;

import java.util.ArrayDeque;
import java.util.Deque;

public class StringBuilderWithUndo {

    private StringBuilder currentString;

    private Deque<Snapshot> snapshot;

    private class Snapshot {
        private String state;

        public Snapshot(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    public StringBuilderWithUndo() {
        currentString = new StringBuilder();
        snapshot = new ArrayDeque<>();
    }

    public StringBuilderWithUndo append(String str) {
        saveState();
        currentString.append(str);
        return this;
    }

    public StringBuilderWithUndo insert(int offset, String str) {
        saveState();
        currentString.insert(offset, str);
        return this;
    }

    public StringBuilderWithUndo delete(int start, int end) {
        saveState();
        currentString.delete(start, end);
        return this;
    }

    public StringBuilderWithUndo undo() {
        if (!snapshot.isEmpty()) {
            Snapshot snapshot = this.snapshot.pop();
            currentString = new StringBuilder(snapshot.getState());
        }
        return this;
    }

    public StringBuilderWithUndo print(String message) {
        System.out.println(message + ": " + this);
        return this;
    }

    private void saveState() {
        snapshot.push(new Snapshot(currentString.toString()));
    }

    @Override
    public String toString() {
        return currentString.toString();
    }
}
