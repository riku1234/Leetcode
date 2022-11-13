import java.util.*;

import static java.lang.String.format;

class MaxRequests {
    static class Server {
        private final int index;
        private int numTasks;
        private int endTime;

        Server(int index) {
            this.index = index;
            this.numTasks = 0;
            this.endTime = 0;
        }

        void addTask(int endTime) {
            numTasks++;
            this.endTime = endTime;
        }

        int getEndTime() {
            return endTime;
        }

        int getIndex() {
            return index;
        }

        int getNumTask() {
            return numTasks;
        }
    }

    public static List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        List<Integer> result = new ArrayList<>();
        TreeSet<Server> treeSet = new TreeSet<>(Comparator.comparingInt(Server::getIndex));
        HashMap<Integer, Server> serverMap = new HashMap<>();
        PriorityQueue<Server> busyServers = new PriorityQueue<>(Comparator.comparingInt(Server::getEndTime));

        for (int i=0; i<k; i++) {
            Server server = new Server(i);
            treeSet.add(server);
            serverMap.put(i, server);
        }

        for (int i=0; i<arrival.length; i++) {
            int startTime = arrival[i];
            int endTime = arrival[i] + load[i];
            System.out.printf("Serving request %d s=%d, e=%d%n", i, startTime, endTime);

            while (!busyServers.isEmpty() && busyServers.peek().getEndTime() <= startTime) {
                Server freeServer = busyServers.poll();
                System.out.printf("Free server %d%n", freeServer.getIndex());
                treeSet.add(freeServer);
            }

            int targetIndex = i % k;
            Server bestServer = serverMap.get(targetIndex);
            System.out.printf("Best server index: %d%n", bestServer.getIndex());
            Server ceiling = treeSet.ceiling(bestServer);
            if (ceiling != null) {
                System.out.printf("Ceiling server %d%n", ceiling.getIndex());
                treeSet.remove(ceiling);
                ceiling.addTask(endTime);
            } else {
                Server first = treeSet.pollFirst();
                if (first != null) {
                    System.out.printf("First server %d%n", first.getIndex());
                    treeSet.remove(first);
                    first.addTask(endTime);
                }
            }
        }

        int maxTasks = 0;
        for (Server server : serverMap.values()) {
            maxTasks = Math.max(maxTasks, server.getNumTask());
        }

        for (Server server : serverMap.values()) {
            if (server.getNumTask() == maxTasks) {
                result.add(server.getIndex());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int k = 3;
        int[] arrival = {1,2,3,4,5};
        int[] load = {5,2,3,3,3};
        System.out.println(busiestServers(k, arrival, load));
    }
}
