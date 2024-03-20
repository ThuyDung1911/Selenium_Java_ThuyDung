## DATN Test Automation eCommerce system with Selenium Java

- Login with Customer Account: check email invalid, password invalid, login success (3 cases)
- Login User Page > Manage Profile: update Profile, add Address (2 cases)
- Login User Page > Get info product > Save to Excel file (1 case)
- Login Admin Page > Add 1 product (1 case)
- Order 2 products with 5 steps (1 case)
    1. My Cart
    2. Shipping info
    3. Delivery info
    4. Payment
    5. Confirmation (checkout)

- Run full flow Order: Add product > Search and Get product info > Checkout this product
- Run all Test cases (9 test cases)
- Write logs to file
- Export Report and Screenshots for test case fail (Extent report)
- Parallel Execution

### Admin page

- URL: https://cms.anhtester.com/login
- Email: admin@example.com
- Password: 123456

### Customer page

- URL: https://cms.anhtester.com/users/login
- Email: customer@example.com
- Password: 123456

### Open Extent Report

- Mở file HTML theo đường dẫn sau khi chạy test: `report/ExtentReport/ExtentReport.html`
