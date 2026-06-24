package kr.ac.kopo.sang.hw2;

import java.util.ArrayList;

public class QuestionRepository {

    public static ArrayList<Question> getAIQuestions() {

        ArrayList<Question> list = new ArrayList<>();

        list.add(new Question(
                "ChatGPT를 개발한 회사는?",
                "Google",
                "OpenAI",
                "Meta",
                "Amazon",
                2));

        list.add(new Question(
                "AI의 뜻은?",
                "Artificial Intelligence",
                "Automatic Internet",
                "Artificial Input",
                "Advanced Interface",
                1));

        list.add(new Question(
                "GPT의 G는?",
                "Google",
                "Graph",
                "Generative",
                "General",
                3));

        list.add(new Question(
                "생성형 AI가 아닌 것은?",
                "ChatGPT",
                "Gemini",
                "Photoshop",
                "Claude",
                3));

        list.add(new Question(
                "마이크로소프트의 AI 비서는?",
                "Gemini",
                "Claude",
                "Copilot",
                "ChatGPT",
                3));

        return list;
    }

    public static ArrayList<Question> getScienceQuestions() {

        ArrayList<Question> list = new ArrayList<>();

        list.add(new Question(
                "물의 화학식은?",
                "H2O",
                "CO2",
                "NaCl",
                "O2",
                1));

        list.add(new Question(
                "지구는 태양을 몇 일에 한 바퀴 공전하는가?",
                "365일",
                "30일",
                "180일",
                "720일",
                1));

        list.add(new Question(
                "빛의 속도는?",
                "300,000 km/s",
                "3,000 km/s",
                "30,000 km/s",
                "300 km/s",
                1));

        list.add(new Question(
                "산소의 원소기호는?",
                "H",
                "O",
                "C",
                "N",
                2));

        list.add(new Question(
                "인체에서 피를 순환시키는 기관은?",
                "폐",
                "위",
                "심장",
                "간",
                3));

        return list;
    }

    public static ArrayList<Question> getHistoryQuestions() {

        ArrayList<Question> list = new ArrayList<>();

        list.add(new Question(
                "세종대왕이 만든 문자는?",
                "한글",
                "한자",
                "가나",
                "영문",
                1));

        list.add(new Question(
                "임진왜란이 일어난 해는?",
                "1592년",
                "1492년",
                "1692년",
                "1792년",
                1));

        list.add(new Question(
                "조선을 건국한 인물은?",
                "이순신",
                "이성계",
                "세종대왕",
                "광해군",
                2));

        list.add(new Question(
                "거북선을 만든 장군은?",
                "강감찬",
                "을지문덕",
                "이순신",
                "김유신",
                3));

        list.add(new Question(
                "대한민국 정부 수립 연도는?",
                "1945년",
                "1948년",
                "1950년",
                "1960년",
                2));

        return list;
    }

    public static ArrayList<Question> getGameQuestions() {

        ArrayList<Question> list = new ArrayList<>();

        list.add(new Question(
                "마인크래프트 제작사는?",
                "Mojang",
                "Valve",
                "Nintendo",
                "Blizzard",
                1));

        list.add(new Question(
                "리그 오브 레전드 제작사는?",
                "Epic Games",
                "Riot Games",
                "Ubisoft",
                "Capcom",
                2));

        list.add(new Question(
                "닌텐도의 대표 캐릭터는?",
                "소닉",
                "링크",
                "마리오",
                "피카츄",
                3));

        list.add(new Question(
                "포켓몬 시리즈를 만든 회사는?",
                "Nintendo",
                "Valve",
                "EA",
                "Rockstar",
                1));

        list.add(new Question(
                "배틀그라운드의 장르는?",
                "RPG",
                "레이싱",
                "배틀로얄",
                "퍼즐",
                3));

        return list;
    }
}