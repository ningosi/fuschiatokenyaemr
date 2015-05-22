

<%
    ui.decorateWith("kenyaemr", "standardPage")

    def menuItems = [
            [ label: "Find or create patient", iconProvider: "kenyaui", icon: "buttons/patient_search.png", href: ui.pageLink("kenyaemr", "registration/registrationSearch") ]
    ]
%>

<div class="ke-page-sidebar">
    ${ ui.includeFragment("kenyaui", "widget/panelMenu", [ heading: "Tasks", items: menuItems ]) }

</div>
<div class="ke-page-content">

    <div id="tabs" class="ke-tabs">
        <ul>
            <li><a href="#metadata-settings">Metadata Settings</a> </li>
            <li><a href="#summary-migrate">Summary and Migrate</a> </li>
        </ul>
        <div id="metadata-settings">
            <form action="display.page" method="post">
                <table>
                    <tr>
                        <td>
                            <input type="text" name="path" id="path" size="50" required="required"/>
                            <em>(set the path here)</em>
                        </td>
                    </tr>
                    <tr><td>&nbsp;</td></tr>
                    <tr>
                        <td>
                            <div class="ke-form-footer">
                                <button type="button"><img src="${ui.resourceLink("kenyaui", "images/glyphs/ok.png")}"/>
                                    Save Path
                                </button>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>

        </div>
        <div id="summary-migrate">
                <table id="list-patients" cellspacing="5px" cellpadding="5px">
                    <thead>
                        <tr>
                            <th colspan="2"><u>Summaries - Fuschia To KenyaEMR</u></th>
                        </tr>

                    </thead>
                    <tbody>
                        <tr>
                            <td>Total Number of patients</td>
                            <td>${ patients }</td>
                        </tr>
                        <tr>
                            <td>Total Visits:</td>
                            <td>${ visits }</td>
                        </tr>

                    </tbody>
                </table>


                <form action="display.page" method="post">
                    <table cellspacing="5px" cellpadding="5px">
                        <tr>
                            <td>Select the database to migrate:</td>
                            <td><input type="file" name="database" size="50"/></td>
                        </tr>
                        <tr>
                            <td>Password (if supported otherwise leave blank):</td>
                            <td><input type="password" id="password" name="passwords" /></td>
                         </tr>
                        <tr>

                            <td></td>
                            <td>
                                <button type="submit"><img
                                        src="${ui.resourceLink("kenyaui", "images/glyphs/ok.png")}"/>Migrate to kenyaEMR
                                </button>
                            </td>
                        </tr>
                    </table>
                </form>

        </div>
    </div>
</div>

<script>
    jQuery(function () {
        var index = 'key';
        var dataStore = window.sessionStorage;
        try {
            var oldIndex = dataStore.getItem(index);
        } catch (e) {
            var oldIndex = 0;
        }

        jQuery("#tabs ").tabs({
            active: oldIndex,
            activate: function (event, ui) {
                var newIndex = ui.newTab.parent().children().index(ui.newTab);
                dataStore.setItem(index, newIndex)
            }
        });
    });
</script>


