package com.example.conferences_sem;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BarbService {
    @Autowired
    private BarbRepository repo;

    public List<Barbershop> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public List<Barbershop> filterByDate(java.sql.Date date) {
        return repo.findByDate(date);
    }

    public void save(Barbershop barbershop) {
        repo.save(barbershop);
    }

    public Barbershop get(Long id) {
        return repo.findById(Math.toIntExact(id)).get();
    }

    public void delete(Long id) {
        repo.deleteById(Math.toIntExact(id));
    }

    public String generateBarChart() throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<Object[]> playData = repo.countByDayChart();

        for (Object[] data : playData) {
            String date = data[0].toString();
            Long count = (Long) data[1];
            dataset.addValue(count, "Кол-во", date);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Количество записей клиентов по дням",
                "Даты",
                "Количество клиентов",
                dataset
        );

        String path = "src/main/resources/static/images/bar_chart.png";
        ChartUtils.saveChartAsPNG(new File(path), chart, 800, 600);
        return "/images/bar_chart.png";
    }

    public Integer averageClients() {
        if (repo.count() > 0) {
            int av = (int) Math.floor(repo.countId() / repo.countDates());
            return av;
        } else {
            return -1;
        }
    }

}
