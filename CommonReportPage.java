@Step("Проверка присутствия записей в секции {sectionName}} и их удаление")
    public static void clearSectionAndAddNewEntry(String sectionName) {
        SelenideElement chooseAll = $(byAttribute("data-test-section", sectionName)).find("mat-checkbox").find("span");
        sleep(1000);
        if (chooseAll.isDisplayed()) {
            chooseAll.click();
            $(byTitle("Удалить")).shouldHave(visible.because("Кнопка удалить не отображается")).click();
            $(byText("Да, удалить")).click(); //delete and add a new entry

            SelenideElement addRecords = $(byAttribute("data-test-section", sectionName)).find(byText("Добавить"));
            Actions actions = new Actions(WebDriverRunner.getWebDriver());
            actions
                    .click(addRecords)
                    .build()
                    .perform();
            finishTime.shouldBe(visible);
            int countRow = $$(byAttribute(ATT_NAME, OPERATION.getAttValue())).size();
            assertThat(countRow)
                    .as("Строка не добавилась в секцию")
                    .isGreaterThan(countRow - 1);
        } else if (!chooseAll.isDisplayed()) {
            SelenideElement addRecords = $(byAttribute("data-test-section", sectionName)).find(byText("Добавить"));
            Actions actions = new Actions(WebDriverRunner.getWebDriver());
            actions
                    .click(addRecords)
                    .build()
                    .perform();
            finishTime.shouldBe(visible);
            int countRow = $$(byAttribute(ATT_NAME, OPERATION.getAttValue())).size();
            assertThat(countRow)
                    .as("Строка не добавилась в секцию")
                    .isGreaterThan(countRow - 1);
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
    
    
