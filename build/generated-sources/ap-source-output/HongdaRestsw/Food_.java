package HongdaRestsw;

import HongdaRestsw.Consumption;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-03-21T16:10:58")
@StaticMetamodel(Food.class)
public class Food_ { 

    public static volatile SingularAttribute<Food, String> servingUnit;
    public static volatile SingularAttribute<Food, BigDecimal> servingAmount;
    public static volatile CollectionAttribute<Food, Consumption> consumptionCollection;
    public static volatile SingularAttribute<Food, String> name;
    public static volatile SingularAttribute<Food, Integer> fat;
    public static volatile SingularAttribute<Food, Integer> id;
    public static volatile SingularAttribute<Food, String> category;
    public static volatile SingularAttribute<Food, Integer> calorieAmount;

}