import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    static class Room {
        int startTime;
        int endTime;
        
        Room(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
    
    public int solution(String[][] book_time) {
        int answer = 0;
        
        List<Room> roomList = new ArrayList<>();
        for (String[] bookTime : book_time) {
            String[] startTimeStr = bookTime[0].split(":");
            String[] endTimeStr = bookTime[1].split(":");
            
            int startTime = Integer.parseInt(startTimeStr[0]) * 60 + Integer.parseInt(startTimeStr[1]);
            int endTime = Integer.parseInt(endTimeStr[0]) * 60 + Integer.parseInt(endTimeStr[1]) + 10;
            
            roomList.add(new Room(startTime, endTime));
        }
        
        Collections.sort(roomList, (o1, o2) -> {
            if (o1.startTime == o2.startTime) {
                return o1.endTime - o2.endTime;
            }
            return o1.startTime - o2.startTime;
         });
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (Room room : roomList) {    
            if (!pq.isEmpty() && pq.peek() <= room.startTime) {
                pq.poll();
            }
            pq.offer(room.endTime);
            answer = Math.max(answer, pq.size());
        }
        
        return answer;
    }
}