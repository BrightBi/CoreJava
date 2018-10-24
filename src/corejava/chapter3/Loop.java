package corejava.chapter3;

public class Loop {

    // 带标签的 break 需要注意，只能跳出循环，不能跳入循环
    public static void main(String[] args) {
        // 对于浮点数，不要使用等于或者不等于来判断，应为不精确，容易产生不可预料的错误
        // floatLoop();
        breakLoop();
        // break 也可以中断 语句块
        // breakIf(11);
        // switchCase(2);
    }

    public static void switchCase(int c) {
    	// case 标签必须是整数或枚举常量（貌似现在String也可以）
        switch (c) {
        case 1 : System.out.println(1);
        case 2 : System.out.println(2);
        }
    }

    public static void breakIf(int middle) {
        loop: {
           int begin = 1;
           int end = 9;
           if (middle >= begin && middle <= end) {
               break loop;
           }
           System.out.println("In if");
        }
        System.out.println("End");
    }

    public static void breakLoop() {
        loop1: for (int i = 0; i < 4; i++) {
            System.out.println("OuterBegin:[" + i + "]");
            if (i == 1) {
                continue;
            }
            for (int j = 0; j < 10; j++) {
                System.out.println("InnerBegin:[" + i + "][" + j + "]");
                if (i == 2) {
                    continue loop1;
                }
                if (i == 3) {
                    break loop1;
                }
                if (j == 1) {
                    continue;
                }
                if (j == 2) {
                    break;
                }
                System.out.println("InnerEnd:[" + i + "][" + j + "]");
            }
            System.out.println("OuterEnd:[" + i + "]");
        }
    }

    public static void floatLoop() {
        for (double i = 0.0; i != 1; i += 0.1) {
            System.out.println(i);
            if (i > 1.2) {
                break;
            }
        }
    }
}
