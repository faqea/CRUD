package dk.robomenden.Robomendenapp.csv;

import dk.robomenden.Robomendenapp.models.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CSVGenerator {

    public String generateCsvStringWinterTask(List<WinterTask> winterTasks) {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("ID,OrderingDate,PickUpDate,DeliveryDate,Route,Address,Address1,Address2,PickUp,Deliver,Client,ClientName,ClientPhone,Street,PostalCode,City,RobotInfo,Status,Seller,Email,Season\n");

        for (WinterTask winterTask : winterTasks) {
            csvBuilder.append(winterTask.getId())
                    .append(",")
                    .append(winterTask.getOrderingDate())
                    .append(",")
                    .append(winterTask.getPickupDate())
                    .append(",")
                    .append(winterTask.getDeliveryDate())
                    .append(",")
                    .append(winterTask.getRoute())
                    .append(",")
                    .append(winterTask.getLocation())
                    .append(",")
                    .append(winterTask.getPreparation1())
                    .append(",")
                    .append(winterTask.getPreparation2())
                    .append(",")
                    .append(winterTask.getPickup())
                    .append(",")
                    .append(winterTask.getDelivery())
                    .append(",")
                    .append(winterTask.getPinCode())
                    .append(",")
                    .append(winterTask.getClient())
                    .append(",")
                    .append(winterTask.getClientName())
                    .append(",")
                    .append(winterTask.getClientPhoneNumber())
                    .append(",")
                    .append(winterTask.getRoad())
                    .append(",")
                    .append(winterTask.getPostNumber())
                    .append(",")
                    .append(winterTask.getCity())
                    .append(",")
                    .append(winterTask.getStatus())
                    .append(",")
                    .append(winterTask.getSeller())
                    .append(",")
                    .append(winterTask.getClientEmail())
                    .append(",")
                    .append(winterTask.getSeason())
                    .append("\n");
        }

        return csvBuilder.toString();
    }

    public String generateCsvStringTask(List<Task> tasks) {

        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("	ID,Type,OriginalDate,SpecifyDate,Time,RowFold,Installer,ConstructionMarket,StoreNumber,RequisitionNumber,Seller,Client,SerialNumber,Campaign1,Campaign2,Producer,TaskStatus,Robot,PinCode,InvoicedManufacturer,InvoicedClient,InvoicedConstructionMarket\n");

        for (Task task : tasks) {
            csvBuilder.append(task.getId())
            		.append(",")
            		.append(task.getTaskType())
                    .append(",")
                    .append(task.getOriginalDate())
                    .append(",")
                    .append(task.getSpecifyDate())
                    .append(",")
                    .append(task.getTime())
                    .append(",")
                    .append(task.getRowFold())
                    .append(",")
                    .append(task.getInstaller())
                    .append(",")
                    .append(task.getConstructionMarket())
                    .append(",")
                    .append(task.getStoreNumber())
                    .append(",")
                    .append(task.getRequisitionNumber())
                    .append(",")
                    .append(task.getSeller())
                    .append(",")
                    .append(task.getClient())
                    .append(",")
                    .append(task.getSerialNumber())
                    .append(",")
                    .append(task.getCampaign1())
                    .append(",")
                    .append(task.getCampaign2())
                    .append(",")
                    .append(task.getProducer())
                    .append(",")
                    .append(task.getTask_status())
                    .append(",")
                    .append(task.getRobot())
                    .append(",")
                    .append(task.getPinCode())
                    .append(",")
                    .append(task.isInvoicedManufacturer())
                    .append(",")
                    .append(task.isInvoicedClient())
                    .append(",")
                    .append(task.isInvoicedConstructionMarket())
                    .append("\n");
        }

        return csvBuilder.toString();
    }

    public String generateCsvStringBuildMarket(List<BuildMarket> buildMarkets) {

        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("ID,Name,Address,PostNumber,City,PhoneNumber,CVRNumber,Email\n");

        for (BuildMarket buildMarket : buildMarkets) {
            csvBuilder.append(buildMarket.getId())
                    .append(",")
                    .append(buildMarket.getName())
                    .append(",")
                    .append(buildMarket.getAddress())
                    .append(",")
                    .append(buildMarket.getPostNumber())
                    .append(",")
                    .append(buildMarket.getCity())
                    .append(",")
                    .append(buildMarket.getPhoneNumber())
                    .append(",")
                    .append(buildMarket.getCVRNumber())
                    .append(",")
                    .append(buildMarket.getEmail())
                    .append("\n");
        }

        return csvBuilder.toString();
    }



    public String generateCsvStringBuyer(List<Buyer> buyers) {

        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("ID,Name,Way,PostIndex,City,Way2,PostIndex2,City2,PhoneNumber,PhoneNumber2,PhoneNumber3,Email\n");

        for (Buyer buyer : buyers) {
            csvBuilder.append(buyer.getId())
                    .append(",")
                    .append(buyer.getName())
                    .append(",")
                    .append(buyer.getWay())
                    .append(",")
                    .append(buyer.getPost_index())
                    .append(",")
                    .append(buyer.getCity())
                    .append(",")
                    .append(buyer.getWay2())
                    .append(",")
                    .append(buyer.getPost_index2())
                    .append(",")
                    .append(buyer.getCity2())
                    .append(",")
                    .append(buyer.getPhone_number())
                    .append(",")
                    .append(buyer.getPhone_number2())
                    .append(",")
                    .append(buyer.getPhone_number3())
                    .append(",")
                    .append(buyer.getEmail())
                    .append("\n");
        }

        return csvBuilder.toString();
    }

    public String generateCsvStringUser(List<User> user) {

        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("ID,Password,Initials,Role,Name,Address,PostalNumber,City,PhoneNumber,PhoneNumber2,Email\n");

        for (User users : user) {
            csvBuilder.append(users.getId())
                    .append(",")
                    .append(users.getPassword())
                    .append(",")
                    .append(users.getInitials())
                    .append(",")
                    .append(users.getRole())
                    .append(",")
                    .append(users.getName())
                    .append(",")
                    .append(users.getAddress())
                    .append(",")
                    .append(users.getPostNumber())
                    .append(",")
                    .append(users.getCity())
                    .append(",")
                    .append(users.getPhoneNumber())
                    .append(",")
                    .append(users.getPhoneNumber2())
                    .append(",")
                    .append(users.getEmail())
                    .append("\n");
        }

        return csvBuilder.toString();
    }

    public String generateCsvStringProducer(List<Producer> producers) {

        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append("ID,Name,Address,PostNumber,City,PhoneNumber,CVRNumber,Email\n");

        for (Producer producer : producers) {
            csvBuilder.append(producer.getId())
                    .append(",")
                    .append(producer.getName())
                    .append(",")
                    .append(producer.getAddress())
                    .append(",")
                    .append(producer.getPostNumber())
                    .append(",")
                    .append(producer.getCity())
                    .append(",")
                    .append(producer.getPhoneNumber())
                    .append(",")
                    .append(producer.getCvrNumber())
                    .append(",")
                    .append(producer.getEmail())
                    .append("\n");
        }

        return csvBuilder.toString();
    }
}
