# Amazon Q Code Transformation: Sample Java 8 Project Built With Maven
Use this to test out Amazon Q Code Transformation!

## Calculator App

This project includes a simple calculator application with a REST API that supports basic arithmetic operations:

### Endpoints

- **Addition**: `/calculator/add?a={number}&b={number}`
- **Subtraction**: `/calculator/subtract?a={number}&b={number}`
- **Multiplication**: `/calculator/multiply?a={number}&b={number}`
- **Division**: `/calculator/divide?a={number}&b={number}`

### Example Usage

```
GET /calculator/add?a=5&b=3
```

Response:
```json
{
  "operation": "+",
  "a": 5.0,
  "b": 3.0,
  "result": 8.0
}
```

### Error Handling

Division by zero will return an error response:

```
GET /calculator/divide?a=5&b=0
```

Response:
```json
{
  "operation": "/",
  "a": 5.0,
  "b": 0.0,
  "error": "Division by zero is not allowed"
}
```