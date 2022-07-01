package ru.home.projects.nasaphototelegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.projects.nasaphototelegrambot.repository.entity.TelegramUser;

import java.util.List;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, String> {

    List<TelegramUser> findAllByActiveTrue();

    List<TelegramUser> findAllBySubscribeTrue();
}
