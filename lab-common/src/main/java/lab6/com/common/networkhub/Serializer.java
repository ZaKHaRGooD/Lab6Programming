package lab6.com.common.networkhub;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializer {
    public Serializer() {
    }

    public byte[] serialize(Serializable obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        oos.flush();
        oos.close();
        baos.close();
        return baos.toByteArray();
    }

    public Serializable deserialize(byte[] obj) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(obj);
        ObjectInputStream objectInput = new ObjectInputStream(bais);
        try {
            return (Serializable) objectInput.readObject();
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
