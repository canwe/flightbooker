package fast.airways.customer;

import fast.airways.customer.Customer;

/**
 * Created by IntelliJ IDEA.
 * User: Bradley.Hart
 * Date: 17-Jul-2010
 * Time: 18:24:35
 * To change this template use File | Settings | File Templates.
 */
public interface CustomerRepository {
    Customer get(long customerId) throws CustomerNotFoundException;


}
