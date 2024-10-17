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
public class ConfService {
    @Autowired
    private ConfRepository repo;

    public List<Conferences> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public List<Conferences> filterByDate(java.sql.Date date) {
        return repo.findByDate(date);
    }

    public void save(Conferences conf) {
        repo.save(conf);
    }

    public Conferences get(Long id) {
        return repo.findById(Math.toIntExact(id)).get();
    }

    public void delete(Long id) {
        repo.deleteById(Math.toIntExact(id));
    }

    public String generateBarChart() throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        List<Object[]> confData = repo.countByDayChart();

        for (Object[] data : confData) {
            String date = data[0].toString();
            Long count = (Long) data[1];
            dataset.addValue(count, "Кол-во", date);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Количество конференций по дням",
                "Даты",
                "Количество конференций",
                dataset
        );

        String path = "src/main/resources/static/images/bar_chart.png";
        ChartUtils.saveChartAsPNG(new File(path), chart, 800, 600);
        return "/images/bar_chart.png";
    }

    public Integer averageConfs() {
        if (repo.count() > 0) {
            int av = (int) Math.floor(repo.countId() / repo.countDates());
            return av;
        } else {
            return -1;
        }
    }

}
