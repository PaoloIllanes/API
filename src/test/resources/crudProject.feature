Feature: Project

  Scenario: create read update delete project


    Given yo uso la authenticacion por Token
    When envio POST request a la /api/projects.json con el body
    """
    {
      "Content":"Cucumber",
      "Icon":"4"
    }
    """
    Then el codigo de respuesta deberia ser 200
    And el expected body deberia ser
    """
    {
        "Id": "IGNORE",
        "Content": "Cucumber",
        "ItemsCount": 0,
        "Icon":4,
        "ItemType": 2,
        "ParentId": null,
        "Collapsed": false,
        "ItemOrder": "IGNORE",
        "Children": [
        ],
        "IsProjectShared": false,
        "ProjectShareOwnerName": null,
        "ProjectShareOwnerEmail": null,
        "IsShareApproved": false,
        "IsOwnProject": true,
        "LastSyncedDateTime": "IGNORE",
        "LastUpdatedDate": "IGNORE",
        "Deleted": false,
        "SyncClientCreationId": null
    }
    """
    And guardo el Id de la respuesta en ID_PROJ

    When envio GET request a la /api/projects/ID_PROJ.json con el body
    """
    """
    Then el codigo de respuesta deberia ser 200
    And el atributo Content deberia ser Cucumber

    When envio PUT request a la /api/projects/ID_PROJ.json con el body
    """
    {
      "Content":"Cucumber2"
    }
    """
    Then el codigo de respuesta deberia ser 200
    And el atributo Content deberia ser Cucumber2

    When envio DELETE request a la /api/projects/ID_PROJ.json con el body
    """
    """
    Then el codigo de respuesta deberia ser 200
    And el atributo Content deberia ser Cucumber2