package Classes.Products;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface DiscountCapability{
    String makeDiscountCode();
    Discount addDiscountByCode(String value, int capacity) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException;
    void addDiscount();
}
