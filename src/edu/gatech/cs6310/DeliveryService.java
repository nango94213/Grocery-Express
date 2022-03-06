package edu.gatech.cs6310;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.*;

public class DeliveryService {
    TreeMap<String, Store> store_list;
    TreeMap<String, Pilot> pilot_list;
    TreeMap<String, Customer> customer_list;
    Set<String> license_set;

    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        store_list = new TreeMap<String, Store>();
        pilot_list = new TreeMap<String, Pilot>();
        customer_list = new TreeMap<String, Customer>();
        license_set = new HashSet<String>();

        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);
                if (wholeInputLine.startsWith("//")) {
                    continue;
                }

                if (tokens[0].equals("make_store")) {

                    if (store_list.containsKey(tokens[1])) {
                        System.out.println("ERROR:store_identifier_already_exists");
                    } else {
                        Store s = new Store(tokens[1], Integer.parseInt(tokens[2]));
                        store_list.put(tokens[1], s);
                        System.out.println("OK:change_completed");
                    }

                } else if (tokens[0].equals("display_stores")) {
                    for (Map.Entry<String, Store> entry : store_list.entrySet()) {
                        entry.getValue().display();
                    }
                    System.out.println("OK:display_completed");

                } else if (tokens[0].equals("sell_item")) {
                    //System.out.println("store: " + tokens[1] + ", item: " + tokens[2] + ", weight: " + tokens[3]);
                    if (store_list.containsKey(tokens[1])) {
                        Store s = store_list.get(tokens[1]);
                        if (s.item_list.containsKey(tokens[2])) {
                            System.out.println("ERROR:item_identifier_already_exists");
                        } else {
                            s.addItem(tokens[2], tokens[3]);
                            System.out.println("OK:change_completed");
                        }
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }

                } else if (tokens[0].equals("display_items")) {
                    //System.out.println("store: " + tokens[1]);

                    if (store_list.containsKey(tokens[1])) {
                        Store s = store_list.get(tokens[1]);
                        s.displayItems();
                        System.out.println("OK:display_completed");
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }

                } else if (tokens[0].equals("make_pilot")) {
                    //System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
                    //System.out.println(", phone: " + tokens[4] + ", tax: " + tokens[5] + ", license: " + tokens[6] + ", experience: " + tokens[7]);
                    if (pilot_list.containsKey(tokens[1])) {
                        System.out.println("ERROR:pilot_identifier_already_exists");
                    } else {
                        if (license_set.contains(tokens[6])) {
                            System.out.println("ERROR:pilot_license_already_exists");
                        } else {
                            Pilot p = new Pilot(tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6],
                                    Integer.parseInt(tokens[7]));
                            pilot_list.put(tokens[1], p);
                            license_set.add(tokens[6]);
                            System.out.println("OK:change_completed");
                        }
                    }
                } else if (tokens[0].equals("display_pilots")) {
                    for (Map.Entry<String, Pilot> entry : pilot_list.entrySet()) {
                        entry.getValue().display();
                    }
                    System.out.println("OK:display_completed");

                } else if (tokens[0].equals("make_drone")) {
                    //System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", capacity: " + tokens[3] + ", fuel: " + tokens[4]);
                    if (store_list.containsKey(tokens[1])) {
                        Store s = store_list.get(tokens[1]);
                        if (s.drone_list.containsKey(tokens[2])) {
                            System.out.println("ERROR:drone_identifier_already_exists");
                        } else {
                            s.addDrone(tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
                            System.out.println("OK:change_completed");
                        }
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }

                } else if (tokens[0].equals("display_drones")) {
                    //System.out.println("store: " + tokens[1]);

                    if (store_list.containsKey(tokens[1])) {
                        Store s = store_list.get(tokens[1]);
                        s.displayDrones();
                        System.out.println("OK:display_completed");
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }

                } else if (tokens[0].equals("fly_drone")) {
                    //System.out.println("store: " + tokens[1] + ", drone: " + tokens[2] + ", pilot: " + tokens[3]);
                    if (store_list.containsKey(tokens[1])) {
                        Store s = store_list.get(tokens[1]);
                        if (s.drone_list.containsKey(tokens[2])) {
                            if (pilot_list.containsKey(tokens[3])) {
                                Pilot p = pilot_list.get(tokens[3]);
                                Drone d = s.drone_list.get(tokens[2]);

                                if (d.pilot != null) {
                                    Pilot pilotB = d.pilot;
                                    pilotB.drone = null;
                                }

                                if (p.drone != null) {
                                    Drone droneB = p.drone;
                                    droneB.pilot = null;
                                }

                                d.pilot = p;
                                p.drone = d;

                                System.out.println("OK:change_completed");
                            } else {
                                System.out.println("ERROR:pilot_identifier_does_not_exist");
                            }

                        } else {
                            System.out.println("ERROR:drone_identifier_does_not_exist");
                        }
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }

                } else if (tokens[0].equals("make_customer")) {
                    //System.out.print("account: " + tokens[1] + ", first_name: " + tokens[2] + ", last_name: " + tokens[3]);
                    //System.out.println(", phone: " + tokens[4] + ", rating: " + tokens[5] + ", credit: " + tokens[6]);
                    if (customer_list.containsKey(tokens[1])) {
                        System.out.println("ERROR:customer_identifier_already_exists");
                    } else {
                        Customer c = new Customer(tokens[1], tokens[2], tokens[3], tokens[4],
                                Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]));
                        customer_list.put(tokens[1], c);
                        System.out.println("OK:change_completed");
                    }

                } else if (tokens[0].equals("display_customers")) {
                    for (Map.Entry<String, Customer> entry : customer_list.entrySet()) {
                        entry.getValue().display();
                    }
                    System.out.println("OK:display_completed");

                } else if (tokens[0].equals("start_order")) {
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", drone: " + tokens[3] + ", customer: " + tokens[4]);
                    if (store_list.containsKey(tokens[1])) {
                        Store s = store_list.get(tokens[1]);
                        if (s.order_list.containsKey(tokens[2])) {
                            System.out.println("ERROR:order_identifier_already_exists");
                        } else {
                            if (s.drone_list.containsKey(tokens[3])) {
                                if (customer_list.containsKey(tokens[4])) {
                                    Customer c = customer_list.get(tokens[4]);
                                    Drone d = s.drone_list.get(tokens[3]);

                                    Order o = new Order(tokens[1], tokens[2], tokens[3], tokens[4], c.credit, d.capacity);

                                    s.order_list.put(tokens[2], o);
                                    c.order_list.put(tokens[2], o);
                                    d.order_list.put(tokens[2], o);

                                    System.out.println("OK:change_completed");

                                } else {
                                    System.out.println("ERROR:customer_identifier_does_not_exist");
                                }
                            } else {
                                System.out.println("ERROR:drone_identifier_does_not_exist");
                            }
                        }
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }

                } else if (tokens[0].equals("display_orders")) {
                    //System.out.println("store: " + tokens[1]);
                    if (store_list.containsKey(tokens[1])) {
                        Store s = store_list.get(tokens[1]);
                        s.displayOrder();
                        System.out.println("OK:display_completed");
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }
                } else if (tokens[0].equals("request_item")) {
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2] + ", item: " + tokens[3] + ", quantity: " + tokens[4] + ", unit_price: " + tokens[5]);
                    if (store_list.containsKey(tokens[1])) {
                        Store s = store_list.get(tokens[1]);
                        if (s.order_list.containsKey(tokens[2])) {
                            if (s.item_list.containsKey(tokens[3])) {
                                Order o = s.order_list.get(tokens[2]);
                                if (o.order_detail.containsKey(tokens[3])) {
                                    System.out.println("ERROR:item_already_ordered");
                                } else {
                                    Customer c = customer_list.get(s.order_list.get(tokens[2]).customerId);
                                    s.requestItem(tokens[2], tokens[3], Integer.parseInt(tokens[4]),
                                            Integer.parseInt(tokens[5]), c);
                                }
                            } else {
                                System.out.println("ERROR:item_identifier_does_not_exist");
                            }
                        } else {
                            System.out.println("ERROR:order_identifier_does_not_exist");
                        }
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }

                } else if (tokens[0].equals("purchase_order")) {
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);
                    if (store_list.containsKey(tokens[1])) {
                        Store s = store_list.get(tokens[1]);
                        if (s.order_list.containsKey(tokens[2])) {
                            Customer c = customer_list.get(s.order_list.get(tokens[2]).customerId);
                            s.complete(tokens[2], c);
                        } else {
                            System.out.println("ERROR:order_identifier_does_not_exist");
                        }
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }


                } else if (tokens[0].equals("cancel_order")) {
                    //System.out.println("store: " + tokens[1] + ", order: " + tokens[2]);
                    if (store_list.containsKey(tokens[1])) {
                        Store s = store_list.get(tokens[1]);
                        if (s.order_list.containsKey(tokens[2])) {
                            Order o = s.order_list.get(tokens[2]);
                            Drone d = s.drone_list.get(o.droneId);

                            d.remaining_cap += o.total_weight;

                            s.order_list.remove(tokens[2]);
                            customer_list.get(o.customerId).order_list.remove(tokens[2]);
                            s.drone_list.get(o.droneId).order_list.remove(tokens[2]);
                            System.out.println("OK:change_completed");
                        } else {
                            System.out.println("ERROR:order_identifier_does_not_exist");
                        }
                    } else {
                        System.out.println("ERROR:store_identifier_does_not_exist");
                    }

                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;

                } else {
                    System.out.println("command " + tokens[0] + " NOT acknowledged");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("simulation terminated");
        commandLineInput.close();
    }


}
