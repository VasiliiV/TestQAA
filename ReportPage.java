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
                .build()
                .perform();
        $(byText("07:00")).shouldBe(visible);
        $(byAttribute("celltype", "duration"))
                .shouldHave(text("7:00")
                .because("Время продолжительности работ отображается некорректно"));
    }

    @Step("Проверка добавление нескольких записей в секцию {sectionName}}")
    public static void checkMultipleNoteSection() {
        ElementsCollection elementsCollection = $$x("//div[@class='mat-cell cell-center cell-interval mat-column-startTime_finishTime ng-star-inserted']");
        int size = elementsCollection.size();
        assertTrue(size >= 1, "Новая запись не добавилась в секцию");
    }
