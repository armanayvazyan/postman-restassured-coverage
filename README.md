
# RestAssured Postman Endpoint Coverage Checker

Postman Filter which adds functionality to calculate endpoints coverage from postman collection json file.


## Documentation

##### On Initilaizing Filter there are several things you need to pass
- **baseUrl**: base url of your API endpoints,
- **baseUrlPattern**: by default it's put `{{url}}`, but in case your is different please override existing value,
- **regexForId**: this is ignoring dynamic part of your endpoint (for instance IDs), by default regex value is `"\\w+"`(all numbers and letters), but you are free to override it
- **collectionFilePath**: path to your postman collection json file. Plase be sure it is in `resources` folder otherwise you ll get an exception.
- **exportPath**: folder for exporting the coverage report, by default it's on root
- **exportFilename**: name for exporting coverage report, by default it's "coverage"

##### After Test run is complete please use this code to call `export()`

`CoverageReportExporter.export(ExportType.JSON)`
## Authors

- [@armanayvazyan](https://www.github.com/armanayvazyan)

