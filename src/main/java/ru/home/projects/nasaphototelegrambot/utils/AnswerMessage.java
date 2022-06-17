package ru.home.projects.nasaphototelegrambot.utils;

public class AnswerMessage {

    public final static String ASTRONOMY_PICTURE_OF_THE_DAY_COMMAND;
    public final static String HELP_COMMAND;
    public final static String MARS_COMMAND;
    public final static String NO_COMMAND;
    public final static String STAT_COMMAND;
    public final static String START_COMMAND;
    public final static String STOP_COMMAND;
    public final static String SUBSCRIBE_COMMAND;
    public final static String UNKNOWN_COMMAND;
    public final static String SUBSCRIBE_CALLBACK;
    public final static String UNSUBSCRIBE_CALLBACK;
    public final static String ASTRONOMY_PICTURE_OF_THE_DAY_CALLBACK;

    static {
        ASTRONOMY_PICTURE_OF_THE_DAY_COMMAND =
                "Выберите \"<b>Фото сегодняшнего дня</b>\" чтобы получить актуальное на сегодня фото " +
                        "или \"<b>Выбрать дату</b>\" для указания точной даты";
        HELP_COMMAND =
                "\uD83E\uDE90<b>Доступные команды</b>\uD83E\uDE90\n\n"
                        + "%s - \uD83C\uDF0E Nasa каждый день публикует \"Фото дня\". " +
                        "Туда попадают фотографии с космических телескопов, вроде Хаббл, фото астрономов-любителей" +
                        " или другие фото как-либо связанные с космосом. " +
                        "Нажав на кнопку вы сможете получить \"Фото дня\".\n\n"
                        + "%s - ☄️ Данная команда позволяет получить фотографии с марсоходов." +
                        " Всего существует 3 марсохода, 2 из которых на данный момент уже не используются," +
                        " но есть возможность получить фотографии с тех дней, когда они ещё ездили по поверхности Марса. " +
                        "Марсоход Curiosity находится в режиме работы и по сей день.\n" +
                        "Данные марсоходы делают десятки и сотни фотографий за один марсианский день. " +
                        "Это могут быть как красивые снимки рельефа Марса, так и случайные искажения" +
                        " в камере. При нажатии на кнопку вы получите сразу серию из " +
                        "10 случайных фотографий, сделанных в один день с одного выбранного марсохода.\n\n"
                        + "%s - \uD83D\uDCE1 подписаться/отписаться на ежедневную рассылку \"Фото дня\" в 14:00 по мск.\n\n"
                        + "%s - ⚒ Помощь по командам и кнопкам.\n\n";
        MARS_COMMAND =
                "Выберите марсоход, с которого хотите получить фото.\n" +
                        "Загрузка может занять <b>3-5 секунд</b>, т.к. в чат загружается сразу галерея из 10 фотографий.";
        NO_COMMAND =
                "Я не поддерживаю данную команду." +
                        "Чтобы посмотреть список команд щёлкни на панель клавиатуры";
        STAT_COMMAND =
                "\"NasaPhoto Telegram Bot использует %s человек:\\n\" +\n" +
                        "            \"Username           Статус подписки\\n\"";
        START_COMMAND =
                "\"Привет. Я NasaPhoto Telegram Bot \\uD83D\\uDEF0\\n\" +\n" +
                        "            \"Я могу присылать фотографии космоса с сайта Nasa\\uD83C\\uDF0E\\n\" +\n" +
                        "            \"Чтобы воспользоваться моим функционалом, нажми нужную кнопку на появившейся клавиатуре.\\n\" +\n" +
                        "            \"Если хочешь узнать подробнее о каждой кнопке, то нажми <b>help</b>\"";
        STOP_COMMAND =
                "\"Деактивировал все ваши подписки \\\\uD83D\\\\uDE1F.\"";
        SUBSCRIBE_COMMAND =
                "Нажав кнопку <b>\"Подписаться\"</b> вам будет каждый день в 14:00 по мск приходить фото дня.\n" +
                        "Кнопка \"Отменить подписку\" отменяет данную подписку";
        UNKNOWN_COMMAND =
                "Не понимаю вас," +
                        " Чтобы посмотреть список команд щёлкни на панель клавиатуры";
        SUBSCRIBE_CALLBACK =
                "Вы подписаны на фото дня";
        UNSUBSCRIBE_CALLBACK =
                "Вы больше не подписаны на фото дня";
        ASTRONOMY_PICTURE_OF_THE_DAY_CALLBACK =
                "Введите дату, на которую хотите получить фото," +
                        "\nв формате <b>YYYY-MM-DD</b>\n" +
                        "Например: 2020-08-26\n" +
                        "Самая ранняя возможная дата - 1995-06-16";
    }
}
