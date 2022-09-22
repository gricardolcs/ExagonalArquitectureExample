import React, { useCallback, useEffect, useState } from 'react';
import PropTypes from 'prop-types';
import CustomTab from '../../atoms/customTab/CustomTab';
import './style.css';
import { Menu, Tab } from 'semantic-ui-react';

function CustomSubMenu({ data }) {

    const [panes, setPanes] = useState([]);

    const loadData = useCallback(() => {
        const items = data.map((item) => {
            return {
                menuItem:
                    <Menu.Item key={item.title} className='subMenuTab default-subTab-selected'>
                        <p>{item.title}</p>
                    </Menu.Item>,
                render: () =>
                    <Tab.Pane className='subMenuContent default-tab-content'>
                        {item.content}
                    </Tab.Pane>
            }
        });
        setPanes(items);
    }, [data])

    useEffect(() => {
        loadData();
    }, [loadData])

    return (
        <CustomTab
            panes={panes}
            menu={{
                attached: 'top',
                className: 'subMenuNavBar'
            }}
        />
    );
}

CustomSubMenu.prototype = {
    data: PropTypes.array
}

CustomSubMenu.defaultProps = {
    data: []
}

export default CustomSubMenu;