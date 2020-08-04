package database.systemRefine;

import Check.CheckPhone;
import com.alibaba.fastjson.JSON;
import database.jdbc.ProviderService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Provider {
    private int id;
    private String name;
    private String desc;
    private String contactPerson;
    private String phone;

    public static List<Provider> search(Provider provider) throws SQLException {
        if (provider == null || (provider.getName().trim().length() == 0 && provider.getDesc().trim().length() == 0)) {
            return null;
        }

        List<Provider> collect = new ArrayList<>();
        for (Map<String, Object> objectMap : ProviderService.fuzzyQuery(provider)) {
            collect.add(JSON.parseObject(JSON.toJSONString(objectMap), Provider.class));
        }

        return collect;

    }

    public static boolean check(Provider provider) throws SQLException {
        if (provider.getPhone().trim().length() == 0 || provider.getName().trim().length() == 0 || provider.getContactPerson().trim().length() == 0 || provider.getDesc().trim().length() == 0) {
            return false;
        }

        if (!CheckPhone.check(provider.getPhone())) {
            return false;
        }

        for (Map<String, Object> temp : ProviderService.getAllProviders()) {
            if (!provider.getName().equals(temp.get("name"))) {
                // 供应商姓名重复
                return false;
            }
        }


        return true;
    }

    public static Provider find(String id) {

        Optional<Provider> first = SocketServer.getProviderList().stream().filter(provider -> provider.getId() == Integer.parseInt(id)).findFirst();

        return first.orElse(null);

    }

    public Provider() {

    }

    public Provider(int id) {
        this.id = id;
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
        return "database.system.Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
