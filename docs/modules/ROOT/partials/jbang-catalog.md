

## Catalogs
* [https://github.com/jooq/jbang-catalog/blob/HEAD/jbang-catalog.json](https://github.com/jooq/jbang-catalog/blob/HEAD/jbang-catalog.json)
* [RodnanSol JBang Catalog](https://github.com/rodnansol/jbang-catalog/blob/HEAD/jbang-catalog.json)

## Aliases

### hello
```bash
jbang hello
```
Script that says hello back for each argument


## Templates

### q-aws-lambda-tf

Quarkus AWS Lambda template with Terraform template. Use the -Dnative-function flag to have native image based Terraform resources
```bash
jbang init -t=q-aws-lambda-tf -Dproperty=value scriptOrFile
```

<details>
    <summary>Properties</summary>

| Name | Description | Default value |
|------|-------------|---------------|
| mode | Quarkus Lambda mode: simple or funq | simple
| tf-providers | If enabled extra Terraform related providers will be generated | false
| tf-provider-aws-version | Version of the AWS Terraform provider | 3.71.0
| tf-provider-archive-version | Version of the Archive Terraform provider | 2.2.0
| tf-provider-null-version | Version of the NULL Terraform provider | 3.1.0
| tf-provider-aws-region | AWS Region | eu-central-1
| aws-vpc-integration | AWSLambdaVPCAccessExecutionRole will be added to the lambda function  | false
| aws-lambda-logging | If logging should be enabled or not | false
| native-function | Native executable based lambda or not | false
| lambda-handler | Lambda handler method&#x27;s name. By default it will be decided by the &#x27;mode&#x27; property, but could be overriden. | 


</details>




