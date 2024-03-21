import java.util.*;

class Solution {
    static class Music implements Comparable<Music>{
        int idx;
        int play;

        Music (int idx, int play) {
            this.idx = idx;
            this.play = play;
        }

        public int compareTo(Music o) {
            if (this.play == o.play) {
                return this.idx - o.idx;
            }
            return o.play - this.play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            genreMap.put(genres[i], genreMap.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> genreList = new ArrayList<>();
        for (String genre : genreMap.keySet()) {
            genreList.add(genre);
        }
        Collections.sort(genreList, (o1, o2) -> genreMap.get(o2) - genreMap.get(o1));

        List<Integer> aList = new ArrayList<>();
        for (String genre : genreList) {
            List<Music> mList = new ArrayList<>();
            for (int i = 0; i < genres.length; i++) {
                if (genre.equals(genres[i])) {
                    mList.add(new Music(i, plays[i]));
                }
            }
            Collections.sort(mList);

            if (mList.size() == 1) {
                aList.add(mList.get(0).idx);
            } else {
                aList.add(mList.get(0).idx);
                aList.add(mList.get(1).idx);
            }
        }
        int[] answer = new int[aList.size()];
        for (int i = 0; i < aList.size(); i++) {
            answer[i] = aList.get(i);
        }
        return answer;
    }

    static void sss(Object s) {
        System.out.println(s);
    }
}