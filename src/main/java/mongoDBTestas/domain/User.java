package mongoDBTestas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    ObjectId id;
    String name;
    Integer accountId;
    Double moneyOnAccount;

}
