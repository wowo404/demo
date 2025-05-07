package org.liu.google.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufExample {
    public static void main(String[] args) {
        Contacts.PeopleInfo.Builder builder = Contacts.PeopleInfo.newBuilder();
        builder.setId(1);
        builder.setName("liu");
        builder.addPhones("15012345670");
        builder.addPhones("15012345671");
        builder.addPhones("15012345672");
        builder.putMp(11, "go1");
        builder.putMp(22, "go2");
        builder.putMp(33, "go3");
        builder.setClientType(BaseInfo.ClientType.MOBILE);
        Contacts.PeopleInfo peopleInfo = builder.build();
        //序列化
        byte[] byteArray = peopleInfo.toByteArray();
        //反序列化
        try {
            Contacts.PeopleInfo peopleInfo1 = Contacts.PeopleInfo.parseFrom(byteArray);
            System.out.println(peopleInfo.equals(peopleInfo1));
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }

    }
}
