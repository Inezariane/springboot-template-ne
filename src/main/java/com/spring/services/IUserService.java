package com.spring.services;

import com.spring.enums.ERole;
import com.spring.models.File;
import com.spring.models.User;
import com.spring.payload.request.UpdateUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;


public interface IUserService {

    Page<User> getAll(Pageable pageable);

    User getById(UUID id);

    User create(User user);
    User save(User user);

    User update(UUID id, UpdateUserDTO dto);

    boolean delete(UUID id);

    Page<User> getAllByRole(Pageable pageable, ERole role);

    Page<User> searchUser(Pageable pageable, String searchKey);

    User getLoggedInUser();

    User getByEmail(String email);

    User changeProfileImage(UUID id, File file);

    User removeProfileImage(UUID id);

}