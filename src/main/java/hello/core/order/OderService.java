package hello.core.order;

public interface OderService {
    Oder createOrder(Long memberId, String itemName, int itemPrice);
}
