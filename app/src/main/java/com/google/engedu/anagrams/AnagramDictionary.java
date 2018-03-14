/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();

    private ArrayList<String> wordList=new ArrayList<>();
    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();
    private HashSet<String> wordSet=new HashSet<>();
    private HashMap<Integer, ArrayList<String>> sizeToWords = new HashMap<>();
    private ArrayList<String> someList=new ArrayList<>();



    public AnagramDictionary(InputStream reader) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(reader));
        String line;
        ArrayList<String> wordMapList;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            wordList.add(word);
            wordSet.add(word);
            //ArrayList<String> sortword = new ArrayList<>();
            if(lettersToWord.containsKey(word.length())){
                wordMapList = sizeToWords.get(word.length());
                wordMapList.add(word);
                sizeToWords.put(word.length(),wordMapList);
            }
            else{
                ArrayList<String> newWordList = new ArrayList<>();
                newWordList.add(word);
                sizeToWords.put(word.length(), newWordList);
            }
            if(lettersToWord.containsKey(sortLetters(word))){
               ArrayList <String> words = lettersToWord.get(sortLetters(word));
               words.add(word);
               lettersToWord.put(sortLetters(word),words);
            }
            else{
                ArrayList <String> listWords = new ArrayList<>();
                listWords.add(word);
                lettersToWord.put(sortLetters(word),listWords);
            }


        }
    }

    public boolean isGoodWord(String word, String base) {
        return wordSet.contains(word) && !word.contains(base);
    }

    public ArrayList<String> getAnagrams(String targetWord) {
        return lettersToWord.get(sortLetters(targetWord));
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        return result;
    }

    public String pickGoodStarterWord() {
        String abc ="";

       for (int a = 0; a < 62995; a++) {
            int n = random.nextInt(62995);

           //int n2 = lettersToWord.get(n).size();
           //someList.add(lettersToWord.get(a));


            String str = wordList.get(n);
            String str2 = sortLetters(str);
            if (lettersToWord.get(str2).size() < 3) {

            }
            else{
                //int m = random.nextInt(lettersToWord.get(str2).size());
                //String str3 = lettersToWord.get(n).get(m);
            abc = str;
            }
        }
        return abc;
    }


    private String sortLetters(String word){
        char[] s = word.toCharArray();
        Arrays.sort(s);
        String b = Arrays.toString(s);
        return b;
    }
}
