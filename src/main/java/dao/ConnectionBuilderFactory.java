package dao;

import static dao.ConnectionBuilderFactory.contype.another;
import static dao.ConnectionBuilderFactory.contype.simple;

public class ConnectionBuilderFactory
{
    enum contype {another, simple};

    public ConnectionBuilder getConnectionBuilder(String type) {

        if (type.equals(simple)){
        return new SimpleConnectionBuilder();
        } else if (type.equals(another)){
            return new AnotherConnectionBuilder();
        } else{
            System.out.println("Unknown type of connection!");
        return null;
        }
    }
}