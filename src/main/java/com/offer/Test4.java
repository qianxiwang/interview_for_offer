package com.offer;

/**
 * 替换空格
 * <p>
 * <p>
 * 请实现一个函数，把字符串中的每个空格替换成“％20”。
 * 例如输入“我们很高兴。”，则输出“我们％20are％20happy。”。
 */
public class Test4 {

    public static String replaceSpace(String line, String oldStr, String newStr) {
        line = line.replace(oldStr, newStr);
        return line;
    }

    public static String replaceSpace2(String line, String oldStr, String newStr) {
        String[] strs = line.split(oldStr);
        return String.join(newStr, strs);
    }


    /**
     * 思路：
     * 1. 统计空格的个数，计算转换后的字符数组的长度，判断其是否小于字符数组的容量 capacity，如果为假，返回 -1
     * 2. 从后往前取出原始字符数组中的字符，直到没有空格
     * 如果是' '，则执行替换操作
     * 如果不是' '， 则复制该字符
     *
     * @param chars      需要转换的字符数组
     * @param usedLength 字符数组中元素的个数
     * @return 转换后的字符数据中元素的个数，-1表示转换失败
     */
    public static int replaceSpace3(char[] chars, int usedLength) {
        // 判断输入是否合法
        if (chars == null || usedLength < 1) return -1;

        // 统计字符数组中空格字符的个数
        int spaceCnt = 0;
        for (int i = 0; i < usedLength; i++)
            if (chars[i] == ' ') ++spaceCnt;

        // 计算新字符数组所需要的长度
        int newUsedLength = 2 * spaceCnt + usedLength;   // int increment = 2 * spaceCnt;
        if (chars.length < newUsedLength) return -1;  // 字符数组长度太小，转换失败

        int indexOfOriginal = usedLength - 1;  // 指向未移动的字符串的末尾，也即是指向待移动的字符
        int indexOfNew = newUsedLength - 1;   // 指向移动后的字符串的开头
        // 从后往前移动字符数组的元素
        // while (indexOfOriginal >= 0 && indexOfOriginal < indexOfNew){  // 当还存在空格时，继续移动字符
        while (spaceCnt > 0) {  // 当还存在空格时，继续移动字符
            if (chars[indexOfOriginal] == ' ') {  // 判断当前字符是为' '，为真则替换' '
                spaceCnt--;
                chars[indexOfNew--] = '0';
                chars[indexOfNew--] = '2';
                chars[indexOfNew--] = '%';
            } else {  // 保存在字符
                chars[indexOfNew--] = chars[indexOfOriginal];
            }
            indexOfOriginal--;  // 字符数组的指针向前移动一位
        }
        return newUsedLength;
    }

    public static void main(String[] args) {
        String line = "We are  happy .";

        String line1 = Test4.replaceSpace(line, " ", "%20");
        System.out.println(line1);

        String line2 = Test4.replaceSpace2(line, " ", "%20");
        System.out.println(line2);

        char[] chars = new char[50];
        char[] string = line.toCharArray();
        for (int i = 0; i < string.length; i++)
            chars[i] = string[i];
        int length = Test4.replaceSpace3(chars, string.length);
        System.out.println(new String(chars, 0, length));
    }

}
