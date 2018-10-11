public class KMP {
    private String pat;
    private int dfa[][];


    public KMP(String pat) {
        //模式字符串构造DFA
        this.pat = pat;
        //模式长度
        int M = pat.length();
        int R = 256;// ACSII 127
        dfa = new int[R][M];
        //初始化dfa
        char[] A = pat.toCharArray();
        dfa[A[0]][0] = 1;
        for (int X = 0, j = 1; j < M; j++) {
            for (int c = 0; c < R; c++) {
                dfa[c][j] = dfa[c][X];//不匹配的情况 用新状态去更新旧状态
            }
            dfa[A[j]][j] = j + 1;
            X = dfa[A[j]][X]; //更新新状态到X中
        }
    }

    public int search(String txt) {
        int i, j, N = txt.length(), M = pat.length();
        char[] S = txt.toCharArray();
        for (i = 0, j = 0; i < N && j < M; ++i) {
            j = dfa[S[i]][j];
        }
        if (j == M) return i - M; //找到匹配
        else
            return N; //没找到匹配
    }

    public static void main(String[] args) {
        String test = "abcdeabc";
        KMP k = new KMP(test);
    }

}