package HongdaRestsw;

import HongdaRestsw.Food;
import HongdaRestsw.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-21T16:10:58")
@StaticMetamodel(Consumption.class)
public class Consumption_ { 

    public static volatile SingularAttribute<Consumption, Date> date;
    public static volatile SingularAttribute<Consumption, Integer> quantity;
    public static volatile SingularAttribute<Consumption, Food> foodId;
    public static volatile SingularAttribute<Consumption, Integer> id;
    public static volatile SingularAttribute<Consumption, Users> userId;

}