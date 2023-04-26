// this questions requires lazy eval

List<Customer> customers = new ArrayList<>();
double now = 0;
for (int i = 0; i < numOfCustomers; i++) {
    Customer customer = new Customer();
    customer.setArrivalTime(now);
    customer.setServiceTime(() -> rng.genServiceTime());
    customers.add(customer);
    now += rng.genInterArrivalTime();
}

class Customer {
  Supplier<double> serviceTime;
  double arrivalTime;

  void setArrivalTime(double arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  void setServiceTime(Supplier<double> serviceTime) {
    this.serviceTime = serviceTime;
  }

  double getArrivalTime() {
    return this.arrivalTime;
  }

  double getServiceTime() {
    return this.serviceTime.get();
  }
}