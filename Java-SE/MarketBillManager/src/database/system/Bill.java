package database.system;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Bill {
    private int id;
    private String product;// 商品描述
    private String providerId;
    private String money;
    private int isPay;
    private String updateTime;
    private String isPayStr;
    private String providerName;

    public static List<Bill> search(Bill bill) {
        if (bill == null || (bill.getProduct().trim().length() == 0 && bill.getIsPay() == -1)) {
            return null;
        }

        List<Bill> collect = SocketServer.getBillList().stream().filter(new Predicate<Bill>() {
            @Override
            public boolean test(Bill u) {
                if (bill.getProduct().trim().length() == 0 && bill.getIsPay() != -1) {
                    return u.getIsPay() == bill.getIsPay();
                } else if (bill.getProduct().trim().length() != 0 && bill.getIsPay() == -1) {
                    return u.getProduct().contains(bill.getProduct().trim());
                }

                return u.getProduct().contains(bill.getProduct().trim()) && u.getIsPay() == bill.getIsPay();
            }
        }).collect(Collectors.toList());

        return collect;
    }

    public static boolean check(Bill bill) {
        if (bill.getProduct().trim().length() == 0 || bill.getMoney().trim().length() == 0) {
            return false;
        }

        return true;
    }


    public Bill(String product, int isPay) {
        this.product = product;
        this.isPay = isPay;
    }

    public Bill() {
    }

    public String getProviderName() {
        return providerName;
    }

//    public void setProviderName(int providerId) {
//        Optional<database.system.Provider> first = database.system.SocketServer.getProviderList().stream().filter(provider -> provider.getId() == providerId).findFirst();
//        first.ifPresent(provider -> this.providerName = provider.getName());
//    }


    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsPayStr() {
        return isPayStr;
    }

    public void setIsPayStr(int isPay) {
        if (isPay == 0) {
            isPayStr = "未付款";
        } else {
            isPayStr = "已付款";
        }
    }

    public void setIsPayStr(String isPayStr) {
        this.isPayStr = isPayStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }

    @Override
    public String toString() {
        return "database.system.Bill{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", providerId='" + providerId + '\'' +
                ", money='" + money + '\'' +
                ", isPay=" + isPay +
                ", updateTime='" + updateTime + '\'' +
                ", isPayStr='" + isPayStr + '\'' +
                ", providerName='" + providerName + '\'' +
                '}';
    }
}
