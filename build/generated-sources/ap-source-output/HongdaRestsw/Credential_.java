package HongdaRestsw;

import HongdaRestsw.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-21T16:10:58")
@StaticMetamodel(Credential.class)
public class Credential_ { 

    public static volatile SingularAttribute<Credential, String> password;
    public static volatile SingularAttribute<Credential, Date> signUpDate;
    public static volatile SingularAttribute<Credential, Integer> userId;
    public static volatile SingularAttribute<Credential, Users> users;
    public static volatile SingularAttribute<Credential, String> username;

}