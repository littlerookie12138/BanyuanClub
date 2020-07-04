package club.banyuan.wordCount;

import java.util.*;

public class WordExample {

  /**
   * 统计每个单词的出现次数，并按照格式输出
   * 单词1=xx次
   * 单词2=xx次
   * 单词3=xx次
   */
  public static final String words = "Shall I compare thee to a summer's day?\n"
          + "Thou art more lovely and more temperate:\n"
          + "Rough winds do shake the darling buds of May,\n"
          + "And summer's lease hath all too short a date:\n"
          + "Sometime too hot the eye of heaven shines,\n"
          + "And often is his gold complexion dimmed,\n"
          + "And every fair from fair sometime declines,\n"
          + "By chance, or nature's changing course untrimmed:\n"
          + "But thy eternal summer shall not fade,\n"
          + "Nor lose possession of that fair thou ow'st,\n"
          + "Nor shall death brag thou wand'rest in his shade,\n"
          + "When in eternal lines to time thou grow'st,\n"
          + "So long as men can breathe or eyes can see,\n"
          + "So long lives this, and this gives life to thee.";

  public static void main(String[] args) {
    Scanner sc = new Scanner(words);
    Map<String, Integer> map = new HashMap<String, Integer>();
    while (sc.hasNext()) {
      String word = sc.next();
      System.out.println(word);

      String[] wordCut = word.split(":\\W+");

      Set<String> wordSet = map.keySet();
      for (int i = 0; i < wordCut.length; i++) {
        //如果已经有这个单词了
        if (wordSet.contains(wordCut[i])) {
          Integer number = map.get(wordCut[i]);
          number++;
          map.put(wordCut[i], number);
        } else {
          map.put(wordCut[i], 1);
        }
      }
    }

    Iterator<String> iterator = map.keySet().iterator();
    while (iterator.hasNext()) {
      String word = iterator.next();

//			System.out.printf("单词： "+word+"出现次数："+hashMap.get(word));
      System.out.printf("单词:%-12s 出现次数:%d\n", word, map.get(word));


    }

  }
}
