# Config Backend Challenge 


The goal of this test is to assert (to some degree) your coding, testing, automation and documentation skills. You're given a simple problem, so you can focus on showcasing your techniques. 
**Problem definition** 

The aim of the test is to create a simple HTTP service that stores and returns configurations that satisfy certain conditions. 

**Instructions** 

1. Create a private repository(Github Preferred). 
2. Add me and Brian to your repository. 
3. Create a new branch. 
4. Solve the task and commit your code. Commit often, we like to see small commits that build up to the end result of your test, instead of one final commit with all the code 

**Endpoints** 
Your application MUST conform to the following endpoint structure and return the HTTP status codes appropriate to each operation. Following are the endpoints that should be implemented: 


**Name Method** 
List             GET                /configs 
Create       POST              /configs 
Get            GET                 _configs_{name} 
Update    PUT_PATCH     /configs_{name} 
Delete     DELETE            _configs_{name} 
Query     GET                    /search?metadata.key=value


**Query** 
The query endpoint MUST return all configs that satisfy the query arguments Example1 
curl http:<IP:PORT>_wakanda_api/search?metadata.monitoring.enabled=true Response sample 
```
[
  {
    "name": "datacenter-1",
    "metadata": {
      "monitoring": {
        "enabled": "true"
      },
      "limits": {
        "cpu": {
          "enabled": "false",
          "value": "700m"
        }
      }
    }
  },
  {
    "name": "datacenter-3",
    "metadata": {
      "monitoring": {
        "enabled": "true"
      },
      "limits": {
        "cpu": {
          "enabled": "false",
          "value": "700m"
        }
      }
    }
  }
] 

```
Example 2 
curl http:<IP:PORT>_wakanda_api/search?metadata.limits.cpu.enabled=true Response sample 
```
{
  "name": "datacenter-2",
  "metadata": {
    "monitoring": {
      "enabled": "false"
    },
    "limits": {
      "cpu": {
        "enabled": "true",
        "value": "500m"
      }
    }
  }
}
```