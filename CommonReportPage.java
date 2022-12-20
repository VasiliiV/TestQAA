 @Step("Проверка присутствия записей в секции {sectionName} и их удаление")
    public static void clearSection(String sectionName) {
        SelenideElement chooseAll = $(byAttribute("data-test-section", sectionName)).find("mat-checkbox").find("span");
        sleep(1000);
        if (chooseAll.isDisplayed()) {
            chooseAll.click();
            $(byTitle("Удалить")).shouldHave(visible.because("Кнопка удалить не отображается")).click();
            $(byText("Да, удалить")).click();
        }
    }
    
  
@Step("Добавление записи в секцию {sectionName}")
    public static void addNewEntryBySection(String sectionName) {
            SelenideElement addRecords = $(byAttribute("data-test-section", sectionName)).find(byText("Добавить"));
            Actions actions = new Actions(WebDriverRunner.getWebDriver());
            actions
                    .pause(100)
                    .click(addRecords)
                    .build()
                    .perform();
            finishTime.shouldBe(visible);
            int countRow = $$(byAttribute(ATT_NAME, OPERATION.getAttValue())).size();
            assertThat(countRow)
                    .as("Строка не добавилась в секцию")
                    .isGreaterThan(0);
    }
    
@Step("Проверка начального времени в секции {sectionName}")
    public static void checkStartTime(String sectionName) {
        SelenideElement startTime = $(byAttribute("data-test-section", sectionName)).find("table-intersection-cell");
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        if (!startTime.getText().equals("00:00")) {
            actions
                .doubleClick(startTime)
                .pause(100)
                .sendKeys("0000")
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        }
        $(byText("00:00")).shouldBe(visible);
        $(byAttribute("celltype", "duration"))
                .shouldHave(text("7:00")
                .because("Время продолжительности работ отображается некорректно"));
    }
    
