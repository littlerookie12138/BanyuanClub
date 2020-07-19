import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Provider {
    private int id;
    private String name;
    private String desc;
    private String contactPerson;
    private String phone;

    public static List<Provider> search(Provider provider) {
        if (provider == null || (provider.getName().trim().length() == 0 && provider.getDesc().trim().length() == 0)) {
            return null;
        }

        List<Provider> collect = SocketServer.getProviderList().stream().filter(new Predicate<Provider>() {
            @Override
            public boolean test(Provider u) {
                if (!provider.getName().trim().equals("") && provider.getDesc().trim().equals("")) {
                    return u.getName().contains(provider.getName().trim());
                } else if (provider.getName().trim().equals("") && !provider.getDesc().trim().equals("")) {
                    return u.getDesc().contains(provider.getDesc().trim());
                }
                return u.getName().contains(provider.getName().trim()) || u.getDesc().contains(provider.getDesc().trim());
            }
        }).collect(Collectors.toList());

        return collect;

    }

    public Provider() {

    }

    public Provider(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
