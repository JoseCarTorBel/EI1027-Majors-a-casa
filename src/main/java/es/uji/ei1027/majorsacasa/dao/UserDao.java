package es.uji.ei1027.majorsacasa.dao;

import java.util.Collection;
import es.uji.ei1027.majorsacasa.model.UserDetails;

public interface UserDao {
    UserDetails loadUserByUsername(String username, String password);
    Collection<UserDetails> listAllUsers();
}