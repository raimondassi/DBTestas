package mongoDBTestas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    ObjectId id;
    Integer senderAccountId;
    Integer recepientAccountId;
    Double moneySent;
}
