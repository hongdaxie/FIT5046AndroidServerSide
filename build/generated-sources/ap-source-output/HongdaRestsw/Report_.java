package HongdaRestsw;

import HongdaRestsw.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-21T16:10:58")
@StaticMetamodel(Report.class)
public class Report_ { 

    public static volatile SingularAttribute<Report, Date> date;
    public static volatile SingularAttribute<Report, Integer> totalCaloriesConsumed;
    public static volatile SingularAttribute<Report, Integer> totalCaloriesBurned;
    public static volatile SingularAttribute<Report, Integer> totalStepsTaken;
    public static volatile SingularAttribute<Report, Integer> calorieGoal;
    public static volatile SingularAttribute<Report, Integer> id;
    public static volatile SingularAttribute<Report, Users> userId;

}