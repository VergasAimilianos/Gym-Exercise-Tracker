import React from 'react';
import './ContentBox.css';

import {GymRecord} from '../entities/GymRecord';

interface ContentBoxProps {
    content: GymRecord;
    }
const ReadContentBox: React.FC<ContentBoxProps> = ({ content}) => {
    const [record, setRecord] = React.useState<GymRecord>(content);

    return (
        <div className="content-box read-box">
            <p><strong>Exercise:</strong> {record.exercise}</p>
            <p><strong>Weight:</strong> {record.weight} kg</p>
        </div>
        );
}

export default ReadContentBox;
