package txs.com.chexian.Tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by txs on 2016/12/16.
 */

public class RegularExpression {


    /**
     *
     * 检查
     * @param phoneNumber
     * @return
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
   /* 可接受的电话格式有:
    * ^\\(? : 可以使用 "(" 作为开头
    * (\\d{3}): 紧接着三个数字
    * \\)? : 可以使用")"接续
    * [- ]? : 在上述格式后可以使用具选择性的 "-".
    * (\\d{4}) : 再紧接着三个数字
    * [- ]? : 可以使用具选择性的 "-" 接续.
    * (\\d{4})$: 以四个数字结束.
    * 可以比较下列数字格式:
    * (123)456-78900, 123-4560-7890, 12345678900, (123)-4560-7890     quot;
   */
        String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5}){1}";
        String expression2 = "^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4}){1}";
        CharSequence inputStr = phoneNumber;
   /*创建Pattern*/
        Pattern pattern = Pattern.compile(expression);
   /*将Pattern 以参数传入Matcher作Regular expression*/
        Matcher matcher = pattern.matcher(inputStr);
   /*创建Pattern2*/
        Pattern pattern2 = Pattern.compile(expression2);
   /*将Pattern2 以参数传入Matcher2作Regular expression*/
        Matcher matcher2 = pattern2.matcher(inputStr);

        if (matcher.matches() || matcher2.matches())    // tvTel.setText("");    Toast.makeText(ZhengzeActivity.this, "输入的电话格式不符",Toast.LENGTH_LONG).show();
        {
            isValid = true;

        } else {
            //Toast.makeText(this, "请输入正确的电话格式",Toast.LENGTH_LONG).show();
        }
        return isValid;
    }

    /**
     * 判断字符串是不是汉字
     *
     * @param usernameString
     * @return
     */
    public static boolean isChineseCharactor(String usernameString) {
        boolean isValid = true;
        String expression = "[^\\u4e00-\\u9fa5]+";
        CharSequence inputStr = usernameString;
   /*创建Pattern*/
        Pattern pattern = Pattern.compile(expression);
   /*将Pattern 以参数传入Matcher作Regular expression*/
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())    // tvTel.setText("");    Toast.makeText(ZhengzeActivity.this, "输入的电话格式不符",Toast.LENGTH_LONG).show();
        {
            isValid = false;

        } else {
            //Toast.makeText(this, "请输入正确的电话格式",Toast.LENGTH_LONG).show();
        }
        return isValid;

    }

}
