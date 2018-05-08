package corejava.chapter3;

public class CodePoint {

    public static void main(String[] args) {
        
        char [] ch = Character.toChars(0x10400);
        
        System.out.printf("U+10400 高代理字符: %04x\n", (int)ch[0]);//d801
        System.out.printf("U+10400 低代理字符: %04x\n", (int)ch[1]);//dc00
        
        String s1 = new String(ch);
        System.out.println(s1 + " | " + s1.length() + " | " + s1.codePointCount(0, s1.length()));
        
        String s2 = new String("𐐀Bi");
        System.out.println(s2 + " | " + s2.length() + " | " + s2.codePointCount(0, s2.length()));
        
        everyChar(s2);
        everyCodePoint(s2);
    }
    
    public static void everyChar(String s) {
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            System.out.println((int)c + "|" + c);
        }
    }
    
    public static void everyCodePoint(String s) {
        for (int i=0; i<s.length();) {
            int c = s.codePointAt(i);
            if (Character.isSupplementaryCodePoint(c)) {
                i += 2;
            } else {
                ++i;
            }
            System.out.println(c + "|" + new String(Character.toChars(c)));
        }
    }
}