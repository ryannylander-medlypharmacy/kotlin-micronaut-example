## Serverless Framework

We use the Serverless Framework to create Infra specific to this repository. This includes the two lambda functions, 
the IAM role associated, and the triggers associated to the API Gateway and the Kinesis streams.

The VPC, Postgres, Subnets, Kinesis, API Gateway are all created in the infra project, which can be viewed [here](https://github.com/medlypharmacy/medly-infra.git)


## Prerequisites to deploying the infra to AWS

1. *Medly AWS Profiles* - You should have AWS Profiles configured on your machines for medly-dev, medly-sandbox, 
and any other account you want to deploy. If this is running on CI, the profiles are not required.

2. *Deployable Jar* - The build should have been created by running ``./gradlew build`` from the 
root folder of this repository.

3. The infrastructure should be setup from the Medly Infra project. This includes the Kinesis, Dynamodb, VPC, etc.

4. The migrations should have been run. This cannot be done locally, and you would need to copy 
``<root folder>/migration/build/distributions/example-db-migrate.zip`` and ``<project root/infra/./applymigrations.sh>`` 
to a remote EC2 machine within the same VPC as the RDS cluster to run it. Unzip the ``example-dp-migrate.zip`` file 
and run ``./applymigrations.sh``


## Deploying to AWS
1. Copy ``<<project root>>/application/build/libs/kotlin-micronaut-example-0.1-all.jar`` to ``<<project root>>/infra`` 
which is the current folder

2. Run the  following command to deploy from the infra folder. The stage is tied to the AWS account, as we have one stage per account. 
The accepted values are sandbox, dev, test, uat, prod, depending on what you have access to.
   
   ```serverless --stage=<Stage Name> deploy```
   
3. To remove the deployment, run the following command

    ```serverless --stage=<Stage Name> remove```
