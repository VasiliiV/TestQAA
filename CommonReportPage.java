@Step("Проверка присутствия записей в секции {sectionName}} и их удаление")
    public static void clearSection() {
        sleep(1000);
        if (chooseAllCheckbox.isDisplayed()) {
            chooseAllCheckbox.click();
            $(byTitle("Удалить")).shouldHave(visible.because("Кнопка удалить не отображается")).click();
            $(byText("Да, удалить")).click();
        } else if (!chooseAllCheckbox.isDisplayed()) {
            SelenideElement addRecords = $x("//app-icon-button-text[@data-test-section-toolbar-button=\"DailyOperation__\"][1]");
            Actions actions = new Actions(WebDriverRunner.getWebDriver());
            actions
                    .click(addRecords)
                    .build()
                    .perform();
        }
    }
    
  
    @Step("Добавление записи и проверка времени окончания операции")
    public static void addTimeEndByOperation() {
        $(byAttribute(ATT_NAME, OPERATION.getAttValue())).shouldBe(visible.because("Секция <Операции не отображается>"));
        finishTime.shouldBe(visible);
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions
                .doubleClick(finishTime)
                .pause(100)
                .sendKeys("0700")
                .sendKeys(Keys.ENTER)
                .doubleClick(startTime)
                .pause(100)
                .sendKeys("0000")
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        $(byText("07:00")).shouldBe(visible);
        $(byAttribute("celltype", "duration"))
                .shouldHave(text("7:00")
                .because("Время продолжительности работ отображается некорректно"));
    }
    
    