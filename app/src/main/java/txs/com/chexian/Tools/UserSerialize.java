package txs.com.chexian.Tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import txs.com.chexian.bean.User;

/**
 * Created by txs on 2017/3/7.
 */

public class UserSerialize {
    /**
     * 序列化对象
     *
     * @param user
     * @return
     * @throws IOException
     */
    public  static String  serialize(User user) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(user);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();

        return serStr;
    }
    public static User  deSerialization(String str) throws IOException,
            ClassNotFoundException {

        String redStr = java.net.URLDecoder.decode(str, "UTF-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                redStr.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        User user = (User) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();

        return user ;
    }
}
