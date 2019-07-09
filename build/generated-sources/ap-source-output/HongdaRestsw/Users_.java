package HongdaRestsw;

import HongdaRestsw.Consumption;
import HongdaRestsw.Credential;
import HongdaRestsw.Report;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-21T16:10:58")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, String> address;
    public static volatile SingularAttribute<Users, String> gender;
    public static volatile CollectionAttribute<Users, Consumption> consumptionCollection;
    public static volatile SingularAttribute<Users, String> postcode;
    public static volatile SingularAttribute<Users, BigDecimal> weight;
    public static volatile CollectionAttribute<Users, Report> reportCollection;
    public static volatile SingularAttribute<Users, Credential> credential;
    public static volatile SingularAttribute<Users, String> surname;
    public static volatile SingularAttribute<Users, Date> dob;
    public static volatile SingularAttribute<Users, Short> levelOfActivity;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile SingularAttribute<Users, Integer> id;
    public static volatile SingularAttribute<Users, Integer> stepsPerMile;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, Integer> height;

}