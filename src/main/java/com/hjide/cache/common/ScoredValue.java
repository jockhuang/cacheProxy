package com.hjide.cache.common;

public class ScoredValue implements Comparable<ScoredValue>
{

    private final String value;

    private final Double score;

    public ScoredValue(String value, Double score)
    {
        this.value = value;
        this.score = score;
    }

    public Double getScore()
    {
        return this.score;
    }

    public String getValue()
    {
        return this.value;
    }

    public int hashCode()
    {
        byte result = 1;
        int result1 = 31 * result + (this.score == null ? 0 : this.score.hashCode());
        result1 = 31 * result1 + (this.value == null ? 0 : this.value.hashCode());
        return result1;
    }

    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        else if (obj == null)
        {
            return false;
        }
        else if (!(obj instanceof ScoredValue))
        {
            return false;
        }
        else
        {
            ScoredValue other = (ScoredValue)obj;
            if (this.score == null)
            {
                if (other.score != null)
                {
                    return false;
                }
            }
            else if (!this.score.equals(other.score))
            {
                return false;
            }

            if (this.value == null)
            {
                if (other.value != null)
                {
                    return false;
                }
            }
            else if (!this.value.equals(other.value))
            {
                return false;
            }

            return true;
        }
    }

    public int compareTo(Double o)
    {
        double thisScore = this.score == null ? 0.0D : this.score.doubleValue();
        double otherScore = o == null ? 0.0D : o.doubleValue();
        return Double.compare(thisScore, otherScore);
    }

    public int compareTo(ScoredValue o)
    {
        return o == null ? this.compareTo((Double)Double.valueOf(0.0D)) : this.compareTo((Double)o.getScore());
    }
}
